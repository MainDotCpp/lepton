do
$$
    declare
        m_id integer;
        c_id integer;
    BEGIN
        insert into sys_menu(name, parent_id, path, permission, type) values ('小红书评论管理', 138,'/collector/xhsNoteComment','collector:xhsNoteComment','CATALOG') returning id into c_id;
        insert into sys_role_menu(role_id, menu_id) values (1, c_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('小红书评论管理:菜单', c_id,'','collector:xhsNoteComment:menu','MENU') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('小红书评论管理:创建', c_id,'','collector:xhsNoteComment:create','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('小红书评论管理:更新', c_id,'','collector:xhsNoteComment:update','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('小红书评论管理:删除', c_id,'','collector:xhsNoteComment:delete','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
        insert into sys_menu(name, parent_id, path, permission, type) values ('小红书评论管理:导出', c_id,'','collector:xhsNoteComment:export','BUTTON') returning id into m_id;
        insert into sys_role_menu(role_id, menu_id) values (1, m_id);
    end
$$;
-------------------------------