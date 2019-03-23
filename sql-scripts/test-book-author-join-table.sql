SELECT b.title, a.first_name, a.middle_name, a.last_name
FROM book b
JOIN book_author b_a ON b.id = b_a.book_id
JOIN author a ON b_a.author_id = a.id
ORDER BY last_name, first_name;