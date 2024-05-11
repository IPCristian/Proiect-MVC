INSERT INTO user (last_name, first_name, email, phone_number, password) VALUES
('Popescu', 'Ion', 'popescu_ion@example.com', '0721123456', 'password1'),
('Ionescu', 'Ana', 'ionescu_ana@example.com', '0722345678', 'password2'),
('Georgescu', 'Maria', 'georgescu_maria@example.com', '0723567890', 'password3'),
('Radulescu', 'Mihai', 'radulescu_mihai@example.com', '0724789012', 'password4'),
('Stancu', 'Elena', 'stancu_elena@example.com', '0725901234', 'password5'),
('Florescu', 'Alex', 'florescu_alex@example.com', '0726012345', 'password6'),
('Dumitrescu', 'Cristina', 'dumitrescu_cristina@example.com', '0727123456', 'password7'),
('Constantinescu', 'Andrei', 'constantinescu_andrei@example.com', '0728234567', 'password8'),
('Vasilescu', 'Ioana', 'vasilescu_ioana@example.com', '0729345678', 'password9'),
('Neagu', 'Stefan', 'neagu_stefan@example.com', '0730456789', 'password10');


INSERT INTO author (last_name, first_name) VALUES
('Eminescu', 'Mihai'),
('Sadoveanu', 'Mihail'),
('Bacovia', 'George'),
('Blaga', 'Lucian'),
('Creanga', 'Ion'),
('Coșbuc', 'George'),
('Caragiale', 'Ion Luca'),
('Ursu', 'Nicolae'),
('Călinescu', 'Matei'),
('Petrescu', 'Camil'),
('Liviu','Rebreanu');

INSERT INTO biography (biography, birth_place, author_id) VALUES
('Mihai Eminescu was a Romanian poet, novelist, and journalist, often regarded as the most famous and influential Romanian poet.', 'Ipotesti, Moldavia', 1),
('Mihail Sadoveanu was a Romanian novelist, short story writer, journalist, and political figure.', 'Pascani, Moldavia', 2),
('George Bacovia was a Romanian symbolist poet, best known for his melancholic poems.', 'Bacau, Moldavia', 3),
('Lucian Blaga was a Romanian philosopher, poet, playwright, and novelist.', 'Lancram, Transylvania', 4),
('Ion Creanga was a Romanian writer, storyteller, and schoolteacher.', 'Humulești, Moldavia', 5),
('George Coșbuc was a Romanian poet, translator, teacher, and journalist.', 'Hordou, Moldavia', 6),
('Ion Luca Caragiale was a Romanian playwright, short story writer, poet, theater manager, political commentator, and journalist.', 'Haimanale, Moldavia', 7),
('Nicolae Ursu was a Romanian writer, literary critic, and politician.', 'Barlad, Moldavia', 8),
('Matei Călinescu was a Romanian literary critic and theorist, philosopher, essayist, fiction writer, and journalist.', 'Bucharest, Romania', 9),
('Camil Petrescu was a Romanian playwright, novelist, philosopher, and journalist.', 'Bucharest, Romania', 10);

INSERT INTO genre (name, description) VALUES
('Poetry', 'Literary genre that expresses feelings and thoughts through rhythm and verse'),
('Novel', 'Fictional prose narrative of considerable length and complexity'),
('Drama', 'Literary work intended for performance by actors on a stage');

INSERT INTO publisher (name, location, website) VALUES
('Cartea Romaneasca', 'Bucharest', 'www.cartearomaneasca.ro'),
('Humanitas', 'Bucharest', 'www.humanitas.ro'),
('Polirom', 'Iasi', 'www.polirom.ro'),
('Nemira', 'Bucharest', 'www.nemira.ro'),
('Editura Univers', 'Bucharest', 'www.edituraunivers.ro');

INSERT INTO book (title, author_id, genre_id, publisher_id) VALUES
('Luceafarul', 1, 1, 1),
('Hanul Ancutei', 2, 2, 2),
('Plumb', 3, 1, 3),
('Paleta de Lumina', 4, 1, 1),
('Amintiri din copilarie', 5, 2, 4),
('Balaurul', 5, 1, 5),
('Moara cu noroc', 6, 2, 3),
('Nunta in cer', 6, 1, 4),
('O scrisoare pierduta', 7, 2, 5),
('D-ale carnavalului', 7, 3, 1),
('Poezii', 8, 1, 2),
('Sud', 8, 1, 3),
('Opera poetica', 9, 1, 4),
('Ultima noapte de dragoste, intaia noapte de razboi', 9, 2, 5),
('Morometii', 10, 2, 1),
('Nopți albe', 10, 2, 2),
('Patul lui Procust', 10, 2, 3),
('Romanul adolescentului miop', 10, 2, 4),
('Un om intre oameni', 10, 2, 5);