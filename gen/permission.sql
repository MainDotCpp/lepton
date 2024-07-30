do
$$
    declare
menu_id integer;
BEGIN
insert into sys_menu(name, parent_id, path, permission, type)
values ('用户管理', 0, '/system/user', 'system:user:menu', 'CATALOG') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('用户管理:创建', 0, '', 'system:user:create', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('用户管理:更新', 0, '', 'system:user:update', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('用户管理:删除', 0, '', 'system:user:delete', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('用户管理:导出', 0, '', 'system:user:export', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
end
$$;
-------------------------------
do
$$
    declare
menu_id integer;
BEGIN
insert into sys_menu(name, parent_id, path, permission, type)
values ('角色管理', 0, '/system/role', 'system:role:menu', 'CATALOG') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('角色管理:创建', 0, '', 'system:role:create', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('角色管理:更新', 0, '', 'system:role:update', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('角色管理:删除', 0, '', 'system:role:delete', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('角色管理:导出', 0, '', 'system:role:export', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
end
$$;
-------------------------------
do
$$
    declare
menu_id integer;
BEGIN
insert into sys_menu(name, parent_id, path, permission, type)
values ('租户管理', 0, '/system/tenant', 'system:tenant:menu', 'CATALOG') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('租户管理:创建', 0, '', 'system:tenant:create', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('租户管理:更新', 0, '', 'system:tenant:update', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('租户管理:删除', 0, '', 'system:tenant:delete', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('租户管理:导出', 0, '', 'system:tenant:export', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
end
$$;
-------------------------------
do
$$
    declare
menu_id integer;
BEGIN
insert into sys_menu(name, parent_id, path, permission, type)
values ('菜单管理', 0, '/system/menu', 'system:menu:menu', 'CATALOG') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('菜单管理:创建', 0, '', 'system:menu:create', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('菜单管理:更新', 0, '', 'system:menu:update', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('菜单管理:删除', 0, '', 'system:menu:delete', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('菜单管理:导出', 0, '', 'system:menu:export', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
end
$$;
-------------------------------
do
$$
    declare
menu_id integer;
BEGIN
insert into sys_menu(name, parent_id, path, permission, type)
values ('套餐管理', 0, '/system/sysPackage', 'system:sysPackage:menu', 'CATALOG') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('套餐管理:创建', 0, '', 'system:sysPackage:create', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('套餐管理:更新', 0, '', 'system:sysPackage:update', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('套餐管理:删除', 0, '', 'system:sysPackage:delete', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
insert into sys_menu(name, parent_id, path, permission, type)
values ('套餐管理:导出', 0, '', 'system:sysPackage:export', 'BUTTON') returning id
into menu_id;
insert into sys_role_menu(role_id, menu_id)
values (1, menu_id);
end
$$;
-------------------------------