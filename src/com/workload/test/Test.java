package com.workload.test;

import java.util.List;

import com.workload.domain.Workload;
import com.workload.service.WorkloadService;

public class Test {
	
	private static WorkloadService workloadService;


	public static void main(String[] args) {
		List<Workload> wlList = workloadService.getAllWorkload();
		
		System.out.println(wlList.size());
		
		
	}

}
