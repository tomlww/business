package com.tiny.business.util;

import java.util.List;

public  interface BaseDao<T>
{
  public  int insertData(T paramT) throws Exception;

  public  int deleteDataByPK(Object paramObject) throws Exception;

  public  int deleteData(T paramT) throws Exception;

  public  int updateData(T paramT) throws Exception;

  public  int updateDataByPK(T paramT) throws Exception;

  public  Object queryObjectByPK(Object paramObject) throws Exception;

  public  int queryForInt(T paramT) throws Exception;

  public  List<T> queryForListAll() throws Exception;

  public  List<T> queryForList(T paramT, int paramInt1, int paramInt2) throws Exception;

  //public abstract PaginationBean<T> queryForListByPagination(T paramT, Page paramPage);

  public  T queryObject(T paramT) throws Exception;

  public  List<T> queryForList(T paramT) throws Exception;
}
