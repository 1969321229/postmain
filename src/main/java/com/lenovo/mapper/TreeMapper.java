package com.lenovo.mapper;

import com.lenovo.pojo.Bootstr;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface TreeMapper {
    @Select("select t.* from t_tree t where pid =#{pid}")
    List<Bootstr> getTreeBootstr(@Param("pid")Integer id);

}
