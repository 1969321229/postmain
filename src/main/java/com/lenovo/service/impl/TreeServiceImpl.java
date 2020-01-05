package com.lenovo.service.impl;


import com.lenovo.mapper.TreeMapper;
import com.lenovo.pojo.Bootstr;
import com.lenovo.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {
    @Resource
    private TreeMapper treeMapper;
    @Override
    public List<Bootstr> getTreeBoot() {
        List<Bootstr> treePrant=treeMapper.getTreeBootstr(0);

        getTreeChildren(treePrant); // alt +shift +m 抽取方法
        return treePrant;
    }


    private void getTreeChildren(List<Bootstr> treePrant) {
        for (Bootstr treea : treePrant) {
            List<Bootstr> treeData = treeMapper.getTreeBootstr(treea.getId());// 找到树条目中孩子的集合


            getTreeChildren(treeData);// 递归调用
            System.out.println("123");
            if(treeData.size()>0)    //这个地方不判断的话nodes=[],判断的话nodes=null,加个判断也就是前台树展示的时候,他如果没有子条目,就不让他显示加减号
                treea.setNodes(treeData);// 把孩子的集合给当前树

        }
    }

}
