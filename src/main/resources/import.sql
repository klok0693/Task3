INSERT INTO companies VALUES (1,'Raton', 'company'),(2,'Kashtan', 'company');

INSERT INTO departments VALUES (1,'fff', 'department',1),(2,'sss', 'department',1),(3,'ddd', 'department',2),(4,'hhh', 'department', 2);

INSERT INTO employees VALUES (1,'Victor','Victor','2008-01-01', 'employee', 1),(2,'Ivan','Ivan','2009-01-02', 'employee', 2),(3,'Fedor','Fedor','2010-02-03', 'employee', 3),(4,'Sergey','Sergey','2011-01-04', 'employee', 4);

INSERT INTO users VALUES (1, '1','1','1','1','$2a$10$z3P5CUZ/T1j/IJ/zynlxoezHFSZGYC3zC4x6D1llBYMhx.yg3/oz2','Alex123'), (2,'1','1','1','1', '$2a$10$xuVw9x54FTcX6jb4MmfMX.su0uwIfgnxN2VFXFegtELZVgHxLYb1i','Tom234'), (3,'1','1','1','1', '$2a$10$r2YJ3u6/NxwMB0TK9nJdwuvRk3zWfITm0zPhqtGlOFOB889cRBTxy','Adam345');

INSERT INTO authorities VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO user_authorities VALUES (1, 1), (2, 1), (3, 2);