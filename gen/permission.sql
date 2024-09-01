-------------------------------
do
$$
    declare
        m_id integer;
        c_id integer;
    BEGIN
        insert into sys_menu(name, parent_id, path, permission, type) values ('系统设置管理', 1,'/system/config','system:config','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('系统设置管理:菜单', c_id,'','system:config:menu','MENU') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('系统设置管理:创建', c_id,'','system:config:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('系统设置管理:更新', c_id,'','system:config:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('系统设置管理:删除', c_id,'','system:config:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('系统设置管理:导出', c_id,'','system:config:export','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
    end
$$;
-------------------------------