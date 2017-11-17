<#include "copyright.ftl"/>

package ${spackage}.dao.impl;

import org.springframework.stereotype.Repository;

import cn.javaworker.yeming.core.jdbc.dao.impl.BaseDaoImpl;
import ${spackage}.dao.${pojo}Dao;
import ${spackage}.pojo.${pojo}Do;

<#include "version.ftl"/>

@Repository
public class ${pojo}DaoImpl extends BaseDaoImpl<${pojo}Do> implements ${pojo}Dao {

}