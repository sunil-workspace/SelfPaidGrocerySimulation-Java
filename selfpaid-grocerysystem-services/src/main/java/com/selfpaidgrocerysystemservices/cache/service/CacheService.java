package com.selfpaidgrocerysystemservices.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.selfpaidgrocerysystemservices.dto.CacheMember;

@Component
public class CacheService {
	
	@Autowired
	private CacheMember cacheMember;
	
	@CachePut(value="cacheMember", key="#id")
    public void putMemberIdDetails(String isMember, String memberId){
		cacheMember.setIsMember(isMember);
		cacheMember.setMemberId(memberId);
    }
     
    @Cacheable(value="cacheMember", key="#id")
    public CacheMember getMemberIdDetails(){
        return cacheMember;
    }
     
    @CacheEvict(value = "cacheMember", key = "#id")
    public void evict(long id){
    }

}
