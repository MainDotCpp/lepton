do
$$
    declare
        m_id integer;
        c_id integer;
    BEGIN
        insert into sys_menu(name, parent_id, path, permission, type) values ('租户管理', 0,'/system/tenant','system:tenant:menu','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('租户管理:创建', c_id,'','system:tenant:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('租户管理:更新', c_id,'','system:tenant:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('租户管理:删除', c_id,'','system:tenant:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('租户管理:导出', c_id,'','system:tenant:export','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
    end
$$;
-------------------------------
do
$$
    declare
        m_id integer;
        c_id integer;
    BEGIN
        insert into sys_menu(name, parent_id, path, permission, type) values ('菜单管理', 0,'/system/menu','system:menu:menu','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('菜单管理:创建', c_id,'','system:menu:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('菜单管理:更新', c_id,'','system:menu:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('菜单管理:删除', c_id,'','system:menu:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('菜单管理:导出', c_id,'','system:menu:export','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
    end
$$;
-------------------------------
do
$$
    declare
        m_id integer;
        c_id integer;
    BEGIN
        insert into sys_menu(name, parent_id, path, permission, type) values ('角色管理', 0,'/system/role','system:role:menu','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('角色管理:创建', c_id,'','system:role:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('角色管理:更新', c_id,'','system:role:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('角色管理:删除', c_id,'','system:role:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('角色管理:导出', c_id,'','system:role:export','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
    end
$$;
-------------------------------
do
$$
    declare
        m_id integer;
        c_id integer;
    BEGIN
        insert into sys_menu(name, parent_id, path, permission, type) values ('用户管理', 0,'/system/user','system:user:menu','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('用户管理:创建', c_id,'','system:user:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('用户管理:更新', c_id,'','system:user:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('用户管理:删除', c_id,'','system:user:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('用户管理:导出', c_id,'','system:user:export','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
    end
$$;
-------------------------------
do
$$
    declare
        m_id integer;
        c_id integer;
    BEGIN
        insert into sys_menu(name, parent_id, path, permission, type) values ('套餐管理', 0,'/system/syspackage','system:syspackage:menu','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('套餐管理:创建', c_id,'','system:syspackage:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('套餐管理:更新', c_id,'','system:syspackage:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('套餐管理:删除', c_id,'','system:syspackage:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('套餐管理:导出', c_id,'','system:syspackage:export','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
    end
$$;
-------------------------------