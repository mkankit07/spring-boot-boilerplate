-- Create Roles
INSERT INTO public.roles (role_id, name, system_name, created_at, updated_at, created_by, updated_by, status, is_primary, fk_parent_role_id, level)
VALUES ('a5fe8fb5-549b-4a08-9824-1ae638a0e99a', 'Super Admin', 'SUPER_ADMIN' ,'2024-06-05 10:00:00', '2024-06-05 10:00:00', null , null, 1, false, null, 0);

INSERT INTO public.roles (role_id, name, system_name, created_at, updated_at, created_by, updated_by, status, is_primary, fk_parent_role_id, level)
VALUES ('44817b43-2f0a-410b-8a35-f35c188ca77b', 'Admin', 'ADMIN' ,'2024-06-05 10:00:00', '2024-06-05 10:00:00', null , null, 1, false, 'a5fe8fb5-549b-4a08-9824-1ae638a0e99a', 1);

INSERT INTO public.roles (role_id, name, system_name, created_at, updated_at, created_by, updated_by, status, is_primary, fk_parent_role_id, level)
VALUES ('0fd8e03b-9678-48ae-9e5f-5b54a8769a4e', 'Lead', 'LEAD' ,'2024-06-05 10:00:00', '2024-06-05 10:00:00', null , null, 1, false, '44817b43-2f0a-410b-8a35-f35c188ca77b', 2);

INSERT INTO public.roles (role_id, name, system_name, created_at, updated_at, created_by, updated_by, status, is_primary, fk_parent_role_id, level)
VALUES ('d35c51c4-1220-416d-8646-b206eeb3a9db', 'User', 'USER' ,'2024-06-05 10:00:00', '2024-06-05 10:00:00', null , null,  1, false, '0fd8e03b-9678-48ae-9e5f-5b54a8769a4e', 3);

-- Create super admin user
INSERT INTO public.users (user_id, created_at, updated_at, created_by, updated_by, status, email, full_name, password)
VALUES ('0c8ec17b-9a5b-4396-a522-876101d1e29e', null, null, '0c8ec17b-9a5b-4396-a522-876101d1e29e','0c8ec17b-9a5b-4396-a522-876101d1e29e', 1, 'superAdmin@yopmail.com', 'SuperAdmin','$2a$10$Cdix08VTTvunM.QWFyOGde/hjqzZmu7i/J26brlQ2tQ1G6.X14/ka');

INSERT INTO public.user_roles (fk_role_id, fk_user_id) VALUES ('a5fe8fb5-549b-4a08-9824-1ae638a0e99a', '0c8ec17b-9a5b-4396-a522-876101d1e29e');

