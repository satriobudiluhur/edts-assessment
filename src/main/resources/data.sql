insert into concert (id, capacity, created_at, event_time, name, venue) values(1, 500, current_timestamp, current_timestamp, 'CONCERT TESTING 1', 'CONCERT VENUE 1') on conflict do nothing;
insert into ticket_availability(concert_id, ticket_type, quantity) values(1, 'VVIP', 50) on conflict do nothing;;
insert into ticket_availability(concert_id, ticket_type, quantity) values(1, 'VIP', 100) on conflict do nothing;;
insert into ticket_availability(concert_id, ticket_type, quantity) values(1, 'FESTIVAL', 350) on conflict do nothing;;

insert into concert (id, capacity, created_at, event_time, name, venue) values(2, 300, current_timestamp, current_timestamp, 'CONCERT TESTING 2', 'CONCERT VENUE 2') on conflict do nothing;
insert into ticket_availability(concert_id, ticket_type, quantity) values(2, 'VVIP', 20) on conflict do nothing;
insert into ticket_availability(concert_id, ticket_type, quantity) values(2, 'VIP', 80) on conflict do nothing;
insert into ticket_availability(concert_id, ticket_type, quantity) values(2, 'FESTIVAL', 200) on conflict do nothing;