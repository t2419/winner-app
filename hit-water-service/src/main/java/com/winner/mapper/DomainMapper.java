package com.winner.mapper;


import com.winner.bean.Domain;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mapper
public interface DomainMapper {

    List<Domain> getDomainList();

    @MapKey("domain")
    @Select("select * from t_domain where `type` = #{type}")
    Map<String, Domain> selectDomainsByType(String type);


    @Select("select domain from t_domain where `type` = #{type}")
    List<String> selectDomainByType(String type);


    int insertDomain(@Param("domains") Collection<Domain> domains);

    int insert(Domain domains);

    int deleteDomainByType(String type);

}
