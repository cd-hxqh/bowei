package com.cdhxqh.bowei.utils;

import com.cdhxqh.bowei.bean.JobTask;

import java.util.Comparator;

/**
 * Created by think on 2015/10/30.
 */
public class MyComparator implements Comparator<JobTask> {

    public int compare(JobTask s1, JobTask s2) {
        if(s1.getJPTASK() > s2.getJPTASK()){
            return 1;
        } else if(s1.getJPTASK() <= s2.getJPTASK()) {
            return -1;
        }
        return 0;
    }

}
