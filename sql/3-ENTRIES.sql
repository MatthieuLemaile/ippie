INSERT INTO type(id,name) values (1,'processeur');
INSERT INTO type(id,name) values (2,'carte mère');

INSERT INTO model(id,name,type_id) values (1,'e5500',1);
INSERT INTO model(id,name,type_id) values (2,'proc modèle 2',1);
INSERT INTO model(id,name,type_id) values (3,'cm model 1',2);
INSERT INTO model(id,name,type_id) values (4,'cm model 2',2);

INSERT INTO state(id,name) values (1,'rangé');
INSERT INTO state(id,name) values (2,'utilisé');
INSERT INTO state(id,name) values (3,'prêté');

INSERT INTO component(id,name,introduced,discontinued,state_id,state_details,model_id,details) VALUES (1,'toto','2012-05-02','2014-02-03',1,'dans l\'étagère',1,'bon état');
INSERT INTO component(id,name,introduced,discontinued,state_id,state_details,model_id,details) VALUES (2,'xfgh','2012-05-02','2014-02-03',1,'dans l\'étagère',2,'');
INSERT INTO component(id,name,introduced,discontinued,state_id,state_details,model_id,details) VALUES (3,'tata','2012-05-02','2014-02-03',3,'à Baptiste',2,'');
INSERT INTO component(id,name,introduced,discontinued,state_id,state_details,model_id,details) VALUES (4,'njch','2012-05-02','2014-02-03',1,'dans l\'armoire',3,'un pau grillé');
INSERT INTO component(id,name,introduced,discontinued,state_id,state_details,model_id,details) VALUES (5,'gdei','2012-05-02',null,2,'dans l\'ordi truc',4,'');
