do
$$
    declare
m_id integer;
        c_id integer;
BEGIN
insert into sys_menu(name, parent_id, path, permission, type) values ('客资管理', 2,'/customer/customer','customer:customer','CATALOG') returning id into c_id;
insert into sys_role_menu(role_id, menu_id) values (1, c_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资管理:菜单', c_id,'','customer:customer:menu','MENU') returning id into m_id;
insert into sys_role_menu(role_id, menu_id) values (1, m_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资管理:创建', c_id,'','customer:customer:create','BUTTON') returning id into m_id;
insert into sys_role_menu(role_id, menu_id) values (1, m_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资管理:更新', c_id,'','customer:customer:update','BUTTON') returning id into m_id;
insert into sys_role_menu(role_id, menu_id) values (1, m_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资管理:删除', c_id,'','customer:customer:delete','BUTTON') returning id into m_id;
insert into sys_role_menu(role_id, menu_id) values (1, m_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资管理:导出', c_id,'','customer:customer:export','BUTTON') returning id into m_id;
insert into sys_role_menu(role_id, menu_id) values (1, m_id);
end
$$;


do
$$
    declare
m_id integer;
        c_id integer;
BEGIN
insert into sys_menu(name, parent_id, path, permission, type) values ('客资渠道管理', 2,'/customer/channel','customer:channel','CATALOG') returning id into c_id;
insert into sys_role_menu(role_id, menu_id) values (1, c_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资渠道管理:菜单', c_id,'','customer:channel:menu','MENU') returning id into m_id;
insert into sys_role_menu(role_id, menu_id) values (1, m_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资渠道管理:创建', c_id,'','customer:channel:create','BUTTON') returning id into m_id;
insert into sys_role_menu(role_id, menu_id) values (1, m_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资渠道管理:更新', c_id,'','customer:channel:update','BUTTON') returning id into m_id;
insert into sys_role_menu(role_id, menu_id) values (1, m_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资渠道管理:删除', c_id,'','customer:channel:delete','BUTTON') returning id into m_id;
insert into sys_role_menu(role_id, menu_id) values (1, m_id);
insert into sys_menu(name, parent_id, path, permission, type) values ('客资渠道管理:导出', c_id,'','customer:channel:export','BUTTON') returning id into m_id;
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
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理', 1,'/system/dict','system:dict','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:菜单', c_id,'','system:dict:menu','MENU') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:创建', c_id,'','system:dict:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:更新', c_id,'','system:dict:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:删除', c_id,'','system:dict:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:导出', c_id,'','system:dict:export','BUTTON') returning id into m_id;
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
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理', 1,'/framework/dict','framework:dict','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:菜单', c_id,'','framework:dict:menu','MENU') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:创建', c_id,'','framework:dict:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:更新', c_id,'','framework:dict:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:删除', c_id,'','framework:dict:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:导出', c_id,'','framework:dict:export','BUTTON') returning id into m_id;
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
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理', 1,'/system/dict','system:dict','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:菜单', c_id,'','system:dict:menu','MENU') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:创建', c_id,'','system:dict:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:更新', c_id,'','system:dict:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:删除', c_id,'','system:dict:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('字典管理:导出', c_id,'','system:dict:export','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
    end
$$;
-------------------------------