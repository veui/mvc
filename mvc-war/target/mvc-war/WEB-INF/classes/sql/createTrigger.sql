-- set sqlblanklines on;

CREATE OR REPLACE TRIGGER trigger_client
BEFORE INSERT ON Client
FOR EACH ROW
BEGIN
  SELECT client_sequence.nextval
  INTO :new.client_id
  FROM dual;
END;
/

CREATE OR REPLACE TRIGGER trigger_item
  BEFORE INSERT ON Item
  FOR EACH ROW
BEGIN
  SELECT item_sequence.nextval
    INTO :new.item_id
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER trigger_order
  BEFORE INSERT ON Order_
  FOR EACH ROW
BEGIN
  SELECT order_sequence.nextval
    INTO :new.order_id
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER trigger_department
  BEFORE INSERT ON Department
  FOR EACH ROW
BEGIN
  SELECT department_sequence.nextval
    INTO :new.department_id
    FROM dual;
END;
/

CREATE OR REPLACE TRIGGER trigger_specialty
  BEFORE INSERT ON Specialty
  FOR EACH ROW
BEGIN
  SELECT specialty_sequence.nextval
    INTO :new.specialty_id
    FROM dual;
END;
/
