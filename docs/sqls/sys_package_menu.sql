create table sys_package_menu
(
    sys_package_id bigint not null
        constraint fklloc6gihlmb2jm1avedj0wboh
            references sys_package,
    menu_id        bigint not null
        constraint fkt15pwoaxl5apkkptn1mlq6v4i
            references sys_menu,
    primary key (sys_package_id, menu_id)
);

alter table sys_package_menu
    owner to lepton;

INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 79);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 1);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 78);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 82);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 64);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 67);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 56);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 77);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 81);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 83);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 55);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 62);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 66);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 71);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 74);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 70);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 73);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 65);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 60);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 54);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 75);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 57);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 63);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 72);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 76);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 80);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 58);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 68);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 69);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 61);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (1, 59);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 1);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 72);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 73);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 74);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 75);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 76);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 77);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 60);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 81);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 56);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 83);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 68);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 61);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 63);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 62);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 70);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 58);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 69);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 55);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 67);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 66);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 71);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 64);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 78);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 82);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 65);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 57);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 54);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 80);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 79);
INSERT INTO public.sys_package_menu (sys_package_id, menu_id) VALUES (5, 59);
