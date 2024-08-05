-- Create basic structure.
create table orders (
    id int not null,
    buyer text not null,
    price float8 not null,
    items json not null
);

create table order_archive (
    order_id int not null,
    sourceSystem varchar not null,
    source_system_code varchar not null
);

-- Insert demo data.
insert into orders values (1, 'John Cheese', 13.35, '[{"name": "Red Leicester", "quantity": 1, "pricePerItem": 3.15, "totalPrice": 3.15}, {"name": "Edam", "quantity": 2, "pricePerItem": 2.90, "totalPrice": 5.80}, {"name": "Gouda", "quantity": 1, "pricePerItem": 4.40, "totalPrice": 4.40}]'::json);
insert into orders values (2, 'Terry Brilliant', 10.99, '[{"name": "The Hitchhiker''s Guide to the Galaxy", "quantity": 1, "pricePerItem": 10.99, "totalPrice": 10.99}]'::json);

insert into order_archive values (1, 'testSystem', '007');
insert into order_archive values (2, 'testSystem', '007');
