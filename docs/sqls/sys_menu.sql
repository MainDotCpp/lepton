create table sys_menu
(
    id         bigint generated by default as identity
        primary key,
    cache      boolean      default false                        not null,
    component  varchar(255) default ''::character varying        not null,
    hidden     boolean      default false                        not null,
    icon       varchar(255) default ''::character varying        not null,
    name       varchar(255) default ''::character varying        not null,
    parent_id  bigint       default 0                            not null,
    path       varchar(255) default ''::character varying        not null,
    permission varchar(255) default ''::character varying        not null,
    sort       integer      default 0                            not null,
    type       varchar(255) default 'CATALOG'::character varying not null
        constraint sys_menu_type_check
            check ((type)::text = ANY
                   ((ARRAY ['CATALOG'::character varying, 'MENU'::character varying, 'BUTTON'::character varying])::text[]))
);

comment on table sys_menu is '菜单';

comment on column sys_menu.cache is '是否缓存';

comment on column sys_menu.component is '组件路径';

comment on column sys_menu.hidden is '是否隐藏';

comment on column sys_menu.icon is '菜单图标';

comment on column sys_menu.name is '菜单名称';

comment on column sys_menu.parent_id is '上级菜单';

comment on column sys_menu.path is '菜单路径';

comment on column sys_menu.permission is '权限标识';

comment on column sys_menu.sort is '排序';

comment on column sys_menu.type is '菜单类型';

alter table sys_menu
    owner to lepton;

INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (1, false, '', false, '', '系统设置', 0, '', 'system', 0, 'CATALOG');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (54, false, '', false, '', '租户管理', 1, '/system/tenant', 'system:tenant', 0, 'CATALOG');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (55, false, '', false, '', '租户管理:菜单', 54, '', 'system:tenant:menu', 0, 'MENU');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (56, false, '', false, '', '租户管理:创建', 54, '', 'system:tenant:create', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (57, false, '', false, '', '租户管理:更新', 54, '', 'system:tenant:update', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (58, false, '', false, '', '租户管理:删除', 54, '', 'system:tenant:delete', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (59, false, '', false, '', '租户管理:导出', 54, '', 'system:tenant:export', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (60, false, '', false, '', '菜单管理', 1, '/system/menu', 'system:menu', 0, 'CATALOG');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (61, false, '', false, '', '菜单管理:菜单', 60, '', 'system:menu:menu', 0, 'MENU');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (62, false, '', false, '', '菜单管理:创建', 60, '', 'system:menu:create', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (63, false, '', false, '', '菜单管理:更新', 60, '', 'system:menu:update', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (64, false, '', false, '', '菜单管理:删除', 60, '', 'system:menu:delete', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (65, false, '', false, '', '菜单管理:导出', 60, '', 'system:menu:export', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (66, false, '', false, '', '角色管理', 1, '/system/role', 'system:role', 0, 'CATALOG');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (67, false, '', false, '', '角色管理:菜单', 66, '', 'system:role:menu', 0, 'MENU');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (68, false, '', false, '', '角色管理:创建', 66, '', 'system:role:create', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (69, false, '', false, '', '角色管理:更新', 66, '', 'system:role:update', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (70, false, '', false, '', '角色管理:删除', 66, '', 'system:role:delete', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (71, false, '', false, '', '角色管理:导出', 66, '', 'system:role:export', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (72, false, '', false, '', '用户管理', 1, '/system/user', 'system:user', 0, 'CATALOG');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (73, false, '', false, '', '用户管理:菜单', 72, '', 'system:user:menu', 0, 'MENU');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (74, false, '', false, '', '用户管理:创建', 72, '', 'system:user:create', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (75, false, '', false, '', '用户管理:更新', 72, '', 'system:user:update', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (76, false, '', false, '', '用户管理:删除', 72, '', 'system:user:delete', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (77, false, '', false, '', '用户管理:导出', 72, '', 'system:user:export', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (78, false, '', false, '', '套餐管理', 1, '/system/sysPackage', 'system:sysPackage', 0, 'CATALOG');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (79, false, '', false, '', '套餐管理:菜单', 78, '', 'system:sysPackage:menu', 0, 'MENU');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (80, false, '', false, '', '套餐管理:创建', 78, '', 'system:sysPackage:create', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (81, false, '', false, '', '套餐管理:更新', 78, '', 'system:sysPackage:update', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (82, false, '', false, '', '套餐管理:删除', 78, '', 'system:sysPackage:delete', 0, 'BUTTON');
INSERT INTO public.sys_menu (id, cache, component, hidden, icon, name, parent_id, path, permission, sort, type) VALUES (83, false, '', false, '', '套餐管理:导出', 78, '', 'system:sysPackage:export', 0, 'BUTTON');
