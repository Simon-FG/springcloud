package com.bdfint.backend.framework.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bdfint.backend.framework.util.Reflections;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
/**
 * service基类实现类
 *
 */
public abstract class BasePgServiceImpl<T extends BasePgEntity<T>> implements BasePgService<T> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected Mapper<T> mapper;

    /**
     * 插入
     *
     * @param object T
     */
    @Override
    public int insert(T object) throws Exception {
        return mapper.insertSelective(object);
    }

    /**
     * 更新
     *
     * @param object T
     */
    @Override
    public void update(T object) throws Exception {
        mapper.updateByPrimaryKeySelective(object);
    }

    /**
     * 删除
     *
     * @param ids 删除的ID
     * @return 删除数量
     */
    @Override
    public int delete(int ids) throws Exception {
    	int status=mapper.deleteByPrimaryKey(ids);
        return status;
    }

    /**
     * 根据ID查询
     *
     * @param id 对象id
     * @return Object
     */
    public T get(Object id) throws Exception {
        T entity = mapper.selectByPrimaryKey(id);
        if (entity == null) {
            @SuppressWarnings("unchecked")
            Class<T> entityClass = Reflections.getClassGenricType(getClass());
            entity = entityClass.newInstance();
        }
        return entity;
    }

    /**
     * 查询列表,
     * 参数为空对象，则查询所有，如：getList(new SysUser())
     *
     * @param object 要查询的对象
     * @return List<T>
     */
    public List<T> getList(T object) throws Exception {
        List<T> list = mapper.select(object);
        if (list == null) {
            list = Lists.newArrayList();
        }
        return list;
    }

    /**
     * 分页查询
     *
     * @param object  分页对象
     * @param example Example
     * @return Page<T>
     */
    @Override
	public PageInfo<T> getPage(T object, Example example) throws Exception {
        if (example.getOredCriteria().size() > 0) {
            List<Example.Criteria> oredCriteria = example.getOredCriteria();
            for(Example.Criteria criteria: oredCriteria){
                criteria.andNotEqualTo("delFlag", BasePgEntity.DEL_FLAG_DELETE);
            }
        } else {
            example.createCriteria().andNotEqualTo("delFlag", BasePgEntity.DEL_FLAG_DELETE);
        }
        int pageSize = object.getPageSize() == null ? Global.PAGE_SIZE : object.getPageSize();
        if (pageSize != -1) {
            PageHelper.startPage(object.getPageNum(), pageSize);
        }
        List<T> list = mapper.selectByExample(example);
        PageInfo<T> page = new PageInfo<>(list);
        page.setPageNum(object.getPageNum());
        page.setPageSize(pageSize);
        return page;
    }
}
