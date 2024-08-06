create table sys_role_menu
(
    role_id bigint not null
        constraint fkkeitxsgxwayackgqllio4ohn5
            references sys_role,
    menu_id bigint not null
        constraint fkf3mud4qoc7ayew8nml4plkevo
            references sys_menu,
    primary key (role_id, menu_id)
);

alter table sys_role_menu
    owner to lepton;

INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (10, 1);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (10, 72);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (10, 73);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (10, 74);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (10, 75);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (10, 76);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (10, 77);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (11, 1);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (11, 72);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (11, 73);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (11, 74);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (11, 75);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (11, 76);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (11, 77);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (12, 1);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (12, 72);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (12, 73);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (12, 74);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (12, 75);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (12, 76);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (12, 77);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 55);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 54);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 1);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 78);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 59);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 80);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 61);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 66);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 74);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 68);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 62);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 65);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 67);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 60);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 64);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 77);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 56);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 72);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 76);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 83);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 63);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 70);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 75);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 81);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 71);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 58);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 69);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 57);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 82);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 73);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (13, 79);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 1);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 54);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 55);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 56);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 57);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 58);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 59);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 60);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 61);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 62);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 63);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 64);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 65);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 66);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 67);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 68);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 69);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 70);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 71);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 72);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 73);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 74);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 75);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 76);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 77);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 78);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 79);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 80);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 81);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 82);
INSERT INTO public.sys_role_menu (role_id, menu_id) VALUES (1, 83);
