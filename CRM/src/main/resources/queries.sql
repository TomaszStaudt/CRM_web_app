SELECT customer.id, customer.name, customer.surname, customer.dob
FROM customer;

INSERT INTO crm.customer
VALUES (?, ?, ?);

DELETE FROM crm.customer WHERE id = ?;

UPDATE crm.customer
SET customer.id = ?, customer.name = ?, customer.surname = ?, customer.dob = ?
WHERE customer.id = ?;

SELECT customer.id, customer.name, customer.surname, customer.dob
FROM customer
WHERE customer.surname = ?;

-- SELECT vehicle.id, vehicle.model, vehicle.brand, vehicle.year, vehicle.crn, vehicle.review_date
-- FROM crm.vehicle JOIN crm.order ON vehicle.id = order.vehicle_id
-- WHERE order.;

----------------------

SELECT
  vehicle.id,
  vehicle.model,
  vehicle.brand,
  vehicle.year,
  vehicle.crn,
  vehicle.review_date,
  vehicle.customer_id
FROM crm.vehicle;

INSERT INTO crm.vehicle VALUES (?, ?, ?, ?, ?);

DELETE FROM crm.vehicle
WHERE id = ?;

UPDATE crm.vehicle
SET
  vehicle.model = ?,
  vehicle.brand = ?,
  vehicle.year = ?,
  vehicle.crn = ?,
  vehicle.review_date = ?
WHERE id = ?;

UPDATE crm.order SET vehicle_id = ? WHERE employee_id = ?;

SELECT
  vehicle.id,
  vehicle.model,
  vehicle.brand,
  vehicle.year,
  vehicle.crn,
  vehicle.review_date,
  vehicle.customer_id
FROM crm.vehicle
WHERE vehicle.customer_id = ?;

----------------------

SELECT employee.id, employee.name, employee.surname, employee.address, employee.note, employee.hcost, employee.phone
FROM crm.employee;

INSERT INTO crm.employee VALUES (?, ?, ?, ?, ?, ?);

DELETE FROM crm.employee
WHERE id = ?;

UPDATE crm.employee
SET
  employee.name = ?,
  employee.surname = ?,
  employee.address = ?,
  employee.note = ?,
  employee.hcost = ?,
  employee.phone = ?
WHERE employee.id = ?;

----------------------

INSERT INTO crm.orders VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

DELETE FROM crm.orders
WHERE id = ?;

UPDATE crm.orders
SET
  orders.order_date = ?,
  orders.pstart_date = ?,
  orders.start_date = ?,
  orders.pdescription = ?,
  orders.rdescription = ?,
  orders.status = ?,
  orders.vehicle_id = ?,
  orders.rcost = ?,
  orders.mcost = ?,
  orders.total_hours = ?
WHERE orders.id = ?;

UPDATE crm.orders SET orders.status = ? WHERE orders.id = ?;

SELECT
  orders.order_date,
  orders.pstart_date,
  orders.start_date,
  orders.pdescription,
  orders.rdescription,
  orders.status,
  orders.vehicle_id,
  orders.employee_id,
  orders.rcost,
  orders.mcost,
  orders.total_hours
FROM crm.orders
WHERE orders.id = ?;

SELECT
  orders.id,
  orders.order_date,
  orders.pstart_date,
  orders.start_date,
  orders.pdescription,
  orders.rdescription,
  orders.status,
  orders.vehicle_id,
  orders.employee_id,
  orders.rcost,
  orders.mcost,
  orders.total_hours
FROM crm.orders
WHERE orders.employee_id = ? AND orders.vehicle_id = ?;

SELECT
  orders.order_date,
  orders.pstart_date,
  orders.start_date,
  orders.pdescription,
  orders.rdescription,
  orders.status,
  orders.vehicle_id,
  orders.rcost,
  orders.mcost,
  orders.total_hours
FROM crm.orders
WHERE orders.employee_id = ?;

----------------------







