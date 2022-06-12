INSERT INTO DOMAINS (id, label, ipv4, parentId) SELECT 1, 'com', '1.0.0.7', null WHERE NOT EXISTS (SELECT id FROM DOMAINS WHERE id = 1)
INSERT INTO DOMAINS (id, label, ipv4, parentId) SELECT 2, 'org', '1.0.0.8', null WHERE NOT EXISTS (SELECT id FROM DOMAINS WHERE id = 2)
INSERT INTO DOMAINS (id, label, ipv4, parentId) SELECT 11, 'example', '1.0.7.1', 1 WHERE NOT EXISTS (SELECT id FROM DOMAINS WHERE id = 11)
INSERT INTO DOMAINS (id, label, ipv4, parentId) SELECT 21, 'example', '1.0.8.1', 2 WHERE NOT EXISTS (SELECT id FROM DOMAINS WHERE id = 21)
INSERT INTO DOMAINS (id, label, ipv4, parentId) SELECT 111, 'www', '1.1.7.1', 11 WHERE NOT EXISTS (SELECT id FROM DOMAINS WHERE id = 111)
INSERT INTO DOMAINS (id, label, ipv4, parentId) SELECT 211, 'www', '1.1.8.1', 21 WHERE NOT EXISTS (SELECT id FROM DOMAINS WHERE id = 211)