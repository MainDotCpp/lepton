create table sys_user_tenant
(
    user_id   bigint not null
        constraint fkktfndeh8g0itm7j6mpmvixd9
            references sys_user,
    tenant_id bigint not null
        constraint fkpj9p90r78hasxerh3xwhgnqvr
            references sys_tenant,
    primary key (user_id, tenant_id)
);

alter table sys_user_tenant
    owner to lepton;

INSERT INTO public.sys_user_tenant (user_id, tenant_id) VALUES (1, 1);
INSERT INTO public.sys_user_tenant (user_id, tenant_id) VALUES (1, 23);
