package com.tianyoukeji.platform.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyoukeji.parent.annotation.StateMachineAction;
import com.tianyoukeji.parent.entity.Org;
import com.tianyoukeji.parent.entity.OrgRepository;
import com.tianyoukeji.parent.entity.State;
import com.tianyoukeji.parent.entity.StateRepository;
import com.tianyoukeji.parent.entity.User;
import com.tianyoukeji.parent.entity.template.StateTemplateRepository;
import com.tianyoukeji.parent.service.NamespaceRedisService;
import com.tianyoukeji.parent.service.NamespaceRedisService.RedisNamespace;
import com.tianyoukeji.platform.service.StateTemplateService.Builder;
import com.tianyoukeji.parent.service.StateMachineService;

@Service
public class UserService extends StateMachineService<User> {

	@Autowired
    private RedisConnectionFactory redisConnectionFactory;
	
	@Autowired
	private NamespaceRedisService namespaceRedisService;
	
	@Autowired
	private StateTemplateService stateTemplateService;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private OrgRepository orgRepository;
	
	
	/**
	 * 	创建用户的状态机
	 */
	@Override
	public void init() {
		if(stateRepository.count() == 0) {
			stateTemplateService.entityStateDeploy(getServiceEntity());
		}
	}
	/**
	 * 	返回值表示是否可以继续往下执行后续，前置截面
	 * @param uuid
	 * @return
	 */
	@StateMachineAction
	public void doEnable(Long uuid , StateMachine<String,String> stateMachine) {
		System.out.println(stateMachine.getState().getId());
		System.out.println("enable  动作");
	}
	
	@StateMachineAction
	public void doDisable(Long uuid , StateMachine<String,String> stateMachine) {
		System.out.println("disable  动作");
	}
	
	@StateMachineAction
	public void doSpeak(Long uuid , StateMachine<String,String> stateMachine) {
		System.out.println(new Date());
		System.out.println("speak  动作");
	}
	
	@StateMachineAction
	public void doKick(Long uuid, StateMachine<String,String> stateMachine) {
		User user = findById(uuid);
		user.setEnabled(false);
		save(user);
		RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
		Set<String> sMembers = namespaceRedisService.sMembers(RedisNamespace.USER_TOKEN, user.getUserinfo().getMobile());
		for (String str : sMembers) {
			redisTokenStore.removeAccessToken(str);
		}
		namespaceRedisService.delete(RedisNamespace.USER_TOKEN, user.getUserinfo().getMobile());
		System.out.println("kick  动作");
	}
}
