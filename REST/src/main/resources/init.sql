INSERT INTO public.accounts (id, role,login, password, active, confirmed, creation_date, last_modification_date, version) VALUES ('00000000-0000-0000-0000-000000000001','ADMIN','admin1','admin1',true,false,'2023-06-07 17:00:00','2023-06-07 17:00:00', 1);
INSERT INTO public.personal_data (id, email, first_name, last_name, phone_number) VALUES ('00000000-0000-0000-0000-000000000001', 'admin1@gmail', 'Mariusz', 'Mioduski','123-456-789');
INSERT INTO public.admins (id, work_alarm_phone) VALUES ('00000000-0000-0000-0000-000000000001','112-112-112');

INSERT INTO public.accounts (id, role,login, password, active, confirmed, creation_date, last_modification_date, version) VALUES ('00000000-0000-0000-0000-000000000002','SERVICE_MANAGER','servicemanager1','servicemanager1',true,false,'2023-06-07 17:01:00','2023-06-07 17:01:00', 1);
INSERT INTO public.personal_data (id, email, first_name, last_name, phone_number) VALUES ('00000000-0000-0000-0000-000000000002', 'servicemanager1@gmail', 'Bartek', 'Kowalski','123-456-788');
INSERT INTO public.service_managers (id, department) VALUES ('00000000-0000-0000-0000-000000000002','serwisowy');

INSERT INTO public.accounts (id, role,login, password, active, confirmed, creation_date, last_modification_date, version) VALUES ('00000000-0000-0000-0000-000000000003','SERVICEMAN','serviceman1','serviceman2',true,false,'2023-06-07 17:02:00','2023-06-07 17:02:00', 1);
INSERT INTO public.personal_data (id, email, first_name, last_name, phone_number) VALUES ('00000000-0000-0000-0000-000000000003', 'serviceman1@gmail', 'Jakub', 'Nowak','123-456-787');
INSERT INTO public.servicemen (id,additional_working_persmissions,department,servicemantype) VALUES ('00000000-0000-0000-0000-000000000003','uprawnienia D i E', 'serwisowy','ELECTRICAL_TECHNICIAN');


INSERT INTO public.farm_locations(id, city_name, creation_date, last_modification_date, postal_code, version) VALUES ('00000000-0000-0000-0000-000000000001', 'Szczecin','2023-06-07 17:02:00', '2023-06-07 17:02:00', '87-600',1);

-- INSERT INTO public.service_requests(id, invidual_object_name, additional_remarks, creation_date, last_modification_date, realization_date, reported, request_status, service_request_description, version, farm_location_id, created_id, serviceman_id)
-- VALUES ('00000000-0000-0000-0000-000000000001', 'BN01','Wymiana urządzenia MOXA zakończona sukcesem', '2023-06-07 17:02:00', '2023-06-07 17:02:00','2023-07-07 17:02:00', 'zgłoszone','Zrealizowane','Wymiana urządzenia MOXA',1,'00000000-0000-0000-0000-000000000001','00000000-0000-0000-0000-000000000002','00000000-0000-0000-0000-000000000003' );