create table sys_user_role
(
    user_id bigint not null
        constraint fkb40xxfch70f5qnyfw8yme1n1s
            references sys_user,
    role_id bigint not null
        constraint fkhh52n8vd4ny9ff4x9fb8v65qx
            references sys_role,
    primary key (user_id, role_id)
);

alter table sys_user_role
    owner to lepton;

INSERT INTO public.sys_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO public.sys_user_role (user_id, role_id) VALUES (1, 13);
INSERT INTO public.sys_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO public.sys_user_role (user_id, role_id) VALUES (2, 2);
