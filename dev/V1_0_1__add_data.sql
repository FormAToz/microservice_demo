INSERT INTO public.organizations
VALUES ((select nextval ('public.organizations_id_seq')), 'Fstock', 'Andrey Danilov', '7.danilov@gmail.com', '777777777');
INSERT INTO public.organizations
VALUES ((select nextval ('public.organizations_id_seq')), 'OptimaGrowth', 'Admin', '7.danilov@gmail.com', '777777777');
INSERT INTO public.organizations
VALUES ((select nextval ('public.organizations_id_seq')), 'Fstock', 'Andrey Danilov', '7.danilov@gmail.com', '777777777');

INSERT INTO public.licenses
VALUES ((select nextval ('public.licenses_id_seq')), 1, 'Software Product', 'Fstock', 'complete', 'comment - generated from volume');
INSERT INTO public.licenses
VALUES ((select nextval ('public.licenses_id_seq')), 2, 'Software Product', 'Fstock', 'complete', 'comment - generated from volume');