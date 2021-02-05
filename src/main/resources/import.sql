--
-- /* Populate tabla roles */
INSERT INTO roles (name, description, report_to) VALUES('ROLE_ADMIN', 'Role Admin', '');
INSERT INTO roles (name, description, report_to) VALUES('ROLE_CUSTOMER', 'Role cliente', '');
INSERT INTO roles (name, description, report_to) VALUES('ROLE_USER', 'Role Usuario', '');
INSERT INTO roles (name, description, report_to) VALUES('ROLE_TECHNICIAN', 'Role de Tecnico Reparaciones', '');

--INSERT INTO users (first_name, last_name, email,user_name,password) VALUES(1,'Peter', 'Smith', 'p.smith@pp.com','psmith','123456');
--INSERT INTO users (first_name, last_name, email,user_name,password) VALUES(2,'Juan', 'Escuer', 'j.escuer@pp.com','jescuer','1234567');
--INSERT INTO users (first_name, last_name, email,user_name,password) VALUES(3,'Mat', 'Will', 'm.will@pp.com','mwill','12345678');

INSERT INTO genders(summary,active)VALUES('Male','true');
INSERT INTO genders(summary,active)VALUES('Female','true');
INSERT INTO genders(summary,active)VALUES('X','true');

INSERT INTO users (first_name, last_name, email, address, internal, personal_phone, status_id, active, password) VALUES('Peter', 'Smith', 'p.smith@pp.com','dirrecion', 'internal', '23454323', 1, true, '$2a$10$ZeEajZ1coHa8H6fiJn0oc.Fft0yIzXmZzXP1OM/AOP4ejydkm/SAe');
INSERT INTO users (first_name, last_name, email, address, internal, personal_phone, status_id, active, password) VALUES('Juan', 'Smith', 'j.escuer@pp.com','dirrecion', 'internal', '23454323', 1, true, '$2a$10$ZeEajZ1coHa8H6fiJn0oc.Fft0yIzXmZzXP1OM/AOP4ejydkm/SAe');


INSERT INTO channels (name, configuration, created_at) VALUES('Whatsapp', 'Server1', '2020-01-29T14:18:47.046Z');
INSERT INTO channels (name, configuration, created_at) VALUES('mail', 'stmp', '2020-01-29T14:18:47.046Z');

INSERT INTO clients (client_type, document_type, ID_number,cuit_cuil,first_name,last_name,social_reason,email,address,alternative_address,city,country,zip_code,area_code,phone,alternative_phone,product_service,active,created_at,update_at,gender_id)VALUES(99, 100, '95777215','20-95777215-4','Pablo','Perez','Pablo Perez','pperez@pp.com.ar','Viamonte 675','',1,54,'1053','','11621398292','','Reparacion','true','2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z',1);
INSERT INTO clients (client_type, document_type, ID_number,cuit_cuil,first_name,last_name,social_reason,email,address,alternative_address,city,country,zip_code,area_code,phone,alternative_phone,product_service,active,created_at,update_at,gender_id)VALUES(98, 101, '69995959','20-95777219-3','Clara','Silva','Clara Silva','clarasilva@pp.com.ar','Viamonte 800','',1,54,'1053','','11621398294','','Reparacion3','true','2020-01-30T14:18:47.046Z','2020-01-29T14:18:47.046Z',2);

INSERT INTO categories (summary)VALUES('Reclamos');
INSERT INTO categories (summary)VALUES('Consulta');
INSERT INTO categories (summary)VALUES('Incidente');



INSERT INTO sub_categories (summary,created_at,updated_at,category_id)VALUES('Reclamos Nokia','2020-01-29T14:18:47.046Z','2020-02-29T14:18:47.046Z',1);
INSERT INTO sub_categories (summary,created_at,updated_at,category_id)VALUES('Reclamos Samsung','2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z',1);
INSERT INTO sub_categories (summary,created_at,updated_at,category_id)VALUES('Reclamos Motorolla','2020-01-29T14:18:47.046Z','2020-03-29T14:18:47.046Z',1);

INSERT INTO sub_categories (summary,created_at,updated_at,category_id)VALUES('Consulta Nokia','2020-01-29T14:18:47.046Z','2020-02-29T14:18:47.046Z',2);
INSERT INTO sub_categories (summary,created_at,updated_at,category_id)VALUES('Consulta Samsung','2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z',2);
INSERT INTO sub_categories (summary,created_at,updated_at,category_id)VALUES('Consulta Motorolla','2020-01-29T14:18:47.046Z','2020-03-29T14:18:47.046Z',2);

-- INSERT INTO companies (name, active, created_at,updated_at)VALUES(1,'Claro', 'true', '2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
-- INSERT INTO companies (name, active, created_at,updated_at)VALUES(2,'Tuenti', 'true', '2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
-- INSERT INTO companies (name, active, created_at,updated_at)VALUES(3,'Personal', 'false', '2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
--
-- INSERT INTO habilities (name, experience, created_at,updated_at)VALUES('Vendedor VIP', 'Alta', '2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
-- INSERT INTO habilities (name, experience, created_at,updated_at)VALUES('Vendedor', 'Baja', '2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
--
-- INSERT INTO Organizations (orgname, active, company_created_at,updated_at)VALUES(1,'CallCenter', 'true',2,'2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
-- INSERT INTO Organizations (orgname, active, company_created_at,updated_at)VALUES(2,'CallCenter2', 'true',2,'2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
--
-- INSERT INTO knowledges (title,summary, files)VALUES('Reparacion 1','Reparacion de tarjetas mt300', 'reparacion1.pdf');
-- INSERT INTO knowledges (title,summary, files)VALUES('Reparacion 2','Reparacion de celulares Nokia', 'reparacion2.pdf');
--
-- INSERT INTO permissions (name,created_at)VALUES('Editar','2020-01-29T14:18:47.046Z');
--
--
-- INSERT INTO permissions (name,created_at)VALUES('Editar','2020-01-29T14:18:47.046Z');
-- INSERT INTO permissions (name,created_at)VALUES('Eliminar','2020-01-29T14:18:47.046Z');
--
-- INSERT INTO user_permission (user_permission_id)VALUES(1,1);
-- INSERT INTO user_permission (user_permission_id)VALUES(1,2);
-- INSERT INTO user_permission (user_permission_id)VALUES(2,1);
--
-- INSERT INTO teams (name )VALUES(1,'Dev');
-- INSERT INTO teams (name )VALUES(2,'Vendedores');
-- INSERT INTO teams (name )VALUES(3,'QA');
--
-- INSERT INTO tickets (title,summary, status,user_client_expiration_date,associated_with,priority_files,contact_name,reported_by,created_by,created_at,updated_at)VALUES(1,'tck1','Reparacion de celular Nokia3300','true',1,2,'2020-01-29T14:18:47.046Z',2,1,'','Carlos Murcia','Juan',1,'2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
-- INSERT INTO tickets (title,summary, status,user_client_expiration_date,associated_with,priority_files,contact_name,reported_by,created_by,created_at,updated_at)VALUES(2,'tck2','Reparacion de celular samsung','false',1,1,'2020-01-29T14:18:47.046Z',3,1,'','Pepe Murcia','Carlos',1,'2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
-- INSERT INTO tickets (title,summary, status,user_client_expiration_date,associated_with,priority_files,contact_name,reported_by,created_by,created_at,updated_at)VALUES(3,'tck3','Reparacion de celular Moto g','true',1,1,'2020-01-29T14:18:47.046Z',3,1,'','Juan Murcia','Carlos',1,'2020-01-29T14:18:47.046Z','2020-01-29T14:18:47.046Z');
--
--  INSERT INTO user_tickets(user_id)VALUES(1,1);
--  INSERT INTO user_tickets(user_id)VALUES(1,2);
--  INSERT INTO user_tickets(user_id)VALUES(2,3);
--
