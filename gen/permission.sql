do
$$
    declare
        m_id integer;
        c_id integer;
    BEGIN
        insert into sys_menu(name, parent_id, path, permission, type) values ('订单管理', 139,'/order/order','order:order','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('订单管理:菜单', c_id,'','order:order:menu','MENU') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('订单管理:创建', c_id,'','order:order:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('订单管理:更新', c_id,'','order:order:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('订单管理:删除', c_id,'','order:order:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('订单管理:导出', c_id,'','order:order:export','BUTTON') returning id into m_id;
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
        insert into sys_menu(name, parent_id, path, permission, type) values ('商品管理', 139,'/order/goods','order:goods','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('商品管理:菜单', c_id,'','order:goods:menu','MENU') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('商品管理:创建', c_id,'','order:goods:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('商品管理:更新', c_id,'','order:goods:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('商品管理:删除', c_id,'','order:goods:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('商品管理:导出', c_id,'','order:goods:export','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
    end
$$;
-------------------------------