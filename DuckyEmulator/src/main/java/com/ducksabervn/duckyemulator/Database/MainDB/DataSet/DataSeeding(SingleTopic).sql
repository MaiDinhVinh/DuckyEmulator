USE DuckyEmulator_QuestionDB;
INSERT INTO Classifications (Classification, Description) VALUES
('Beginner', 'Basic concepts and fundamentals suitable for newcomers'),
('Intermediate', 'Topics requiring some prior knowledge and experience'),
('Advanced', 'Complex concepts for experienced learners'),
('Expert', 'Specialized knowledge and advanced techniques'),
('Novice', 'Entry-level concepts with detailed explanations'),
('Upper Beginner', 'Building blocks and basic problem-solving'),
('Lower Intermediate', 'Transitioning from basics to more complex topics'),
('Upper Intermediate', 'Advanced concepts with practical applications'),
('Pre-Advanced', 'Bridge between intermediate and advanced topics'),
('Lower Expert', 'Specialized knowledge with industry relevance'),
('Upper Expert', 'Cutting-edge techniques and best practices'),
('Professional', 'Industry-standard practices and patterns'),
('Senior Professional', 'Architecture-level knowledge and decision making'),
('Master', 'Deep expertise in specific domains'),
('Specialist', 'Focused expertise in niche areas'),
('Domain Expert', 'Comprehensive knowledge of specific domains'),
('Research Level', 'Academic and research-oriented concepts'),
('Industry Expert', 'Real-world implementation expertise'),
('Solution Architect', 'System-wide design and integration knowledge'),
('Technical Lead', 'Leadership-level technical expertise'),
('Principal Engineer', 'High-level system design expertise'),
('Distinguished', 'Industry-leading expertise'),
('Fellow', 'Thought leadership and innovation'),
('Emeritus', 'Lifetime achievement level expertise');
-- Topics Insert Statements
INSERT INTO Topics (TopicName, Description) VALUES
('Python Basics', 'Fundamental concepts of Python programming'),
('Java Core', 'Core Java programming concepts'),
('JavaScript Essentials', 'Essential JavaScript programming concepts'),
('C++ Programming', 'C++ language fundamentals'),
('SQL Fundamentals', 'Basic database query language concepts'),
('HTML/CSS', 'Web markup and styling basics'),
('React Framework', 'React.js development framework'),
('Node.js', 'Server-side JavaScript runtime'),
('Angular', 'Angular framework fundamentals'),
('Vue.js', 'Vue.js framework basics'),
('Django', 'Python web framework'),
('Spring Boot', 'Java Spring Boot framework'),
('Express.js', 'Node.js web application framework'),
('MongoDB', 'NoSQL database concepts'),
('PostgreSQL', 'Advanced relational database'),
('Redis', 'In-memory data structure store'),
('Git Basics', 'Version control fundamentals'),
('Docker Containers', 'Container platform basics'),
('Kubernetes', 'Container orchestration'),
('AWS Services', 'Amazon Web Services platform'),
('Azure Basics', 'Microsoft Azure cloud platform'),
('Google Cloud', 'Google Cloud Platform services'),
('Linux Administration', 'Linux system administration'),
('Windows Server', 'Windows server management'),
('Network Protocols', 'Communication protocols'),
('Cybersecurity Basics', 'Basic security concepts'),
('Encryption', 'Data encryption methods'),
('Authentication', 'User authentication systems'),
('RESTful APIs', 'REST architecture style'),
('GraphQL', 'API query language'),
('Unit Testing', 'Code testing fundamentals'),
('CI/CD Pipeline', 'Continuous integration/deployment'),
('Agile Methods', 'Agile development practices'),
('Scrum', 'Scrum framework implementation'),
('Data Structures', 'Basic data structure concepts'),
('Algorithms', 'Algorithm implementation'),
('Machine Learning', 'ML fundamentals'),
('Deep Learning', 'Neural network concepts'),
('Big Data', 'Large-scale data processing'),
('Data Analytics', 'Data analysis methods'),
('UI Design', 'User interface design'),
('UX Principles', 'User experience concepts'),
('Mobile UI', 'Mobile interface design'),
('iOS Development', 'Apple iOS development'),
('Android Dev', 'Android app development'),
('Flutter', 'Cross-platform development'),
('React Native', 'Mobile app framework'),
('WebAssembly', 'Low-level web programming'),
('Microservices', 'Distributed system design'),
('System Design', 'Architecture principles'),
('Code Quality', 'Code maintenance and quality'),
('Performance Optimization', 'System optimization'),
('Security Testing', 'Security assessment methods'),
('Cloud Security', 'Cloud platform security'),
('Blockchain Dev', 'Blockchain development'),
('IoT Programming', 'IoT device programming'),
('Shell Scripting', 'Command-line automation');
-- Questions Insert Statements
INSERT INTO Questions (ClassificationId, QuestionStatement, CorrectAnswer, Choice1, Choice2, Choice3, Choice4, ImagePath) VALUES
(1, 'What is the correct way to declare a variable in Python?', 'variable_name = value', 'var variable_name = value', 'dim variable_name = value', 'let variable_name = value', 'variable_name : value', NULL),
(1, 'Which of the following is a valid Python comment?', '# This is a comment', '// This is a comment', '/* This is a comment */', '<!-- This is a comment -->', '\'\'This is a comment', NULL),
(2, 'What is the output of: len([1, 2, 3, 4, 5])?', 5, 4, 6, 0, 'Error', NULL),
(3, 'Which of these is not a valid method for string formatting in Python?', 'string << variable', 'f"{variable}"', '"%s" % variable', '"{0}".format(variable)', 'None of the above', NULL),
(1, 'What is the main method signature in Java?', 'public static void main(String[] args)', 'public void main(String[] args)', 'static void main(String[] args)', 'void main(String[] args)', 'main(String[] args)', NULL),
(2, 'Which keyword is used for inheritance in Java?', 'extends', 'implements', 'inherits', 'super', 'parent', NULL),
(1, 'What is the correct way to declare a constant in JavaScript?', 'const NAME = value;', 'constant NAME = value;', 'final NAME = value;', 'let NAME = value;', 'var NAME = value;', NULL),
(2, 'What will be the output of: typeof([]);', 'object', 'array', 'undefined', 'null', 'list', NULL),
(1, 'Which header file is required for input/output in C++?', 'iostream', 'stdio.h', 'conio.h', 'stdlib.h', 'input.h', NULL),
(2, 'What is the correct syntax for a pointer declaration in C++?', 'int* ptr;', 'pointer int ptr;', 'int ptr*;', 'int &ptr;', '@int ptr;', NULL);
-- Continue with more questions...
INSERT INTO Questions (ClassificationId, QuestionStatement, CorrectAnswer, Choice1, Choice2, Choice3, Choice4, ImagePath) VALUES
(1, 'Which SQL command is used to retrieve data from a database?', 'SELECT', 'FETCH', 'GET', 'RETRIEVE', 'EXTRACT', NULL),
(2, 'What is the purpose of the GROUP BY clause in SQL?', 'To group rows that have the same values', 'To sort the result set', 'To filter rows before grouping', 'To join multiple tables', 'To limit the result set', NULL),
(1, 'What does HTML stand for?', 'HyperText Markup Language', 'High Text Markup Language', 'HyperText Markdown Language', 'HighText Markdown Language', 'HyperText Making Language', NULL),
(2, 'Which CSS property is used to change the text color?', 'color', 'text-color', 'font-color', 'text-style', 'foreground-color', NULL),
(1, 'What is JSX in React?', 'JavaScript XML', 'JavaScript Extension', 'JavaScript Syntax', 'Java Syntax Extension', 'JavaScript Extra', NULL),
(2, 'What hook is used for side effects in React?', 'useEffect', 'useState', 'useMemo', 'useCallback', 'useRef', NULL),
(1, 'What is Node.js?', 'JavaScript runtime environment', 'Web browser', 'Programming language', 'Database system', 'Operating system', NULL),
(2, 'Which module is used to create a server in Node.js?', 'http', 'server', 'web', 'net', 'express', NULL),
(1, 'What is the latest major version of Angular?', '17', '15', '14', '16', '13', NULL),
(2, 'Which decorator is used to define a component in Angular?', '@Component', '@NgComponent', '@Template', '@View', '@Angular', NULL);

-- Adding more questions to reach 100...
INSERT INTO Questions (ClassificationId, QuestionStatement, CorrectAnswer, Choice1, Choice2, Choice3, Choice4, ImagePath) VALUES
(1, 'What is Vue.js?', 'Progressive JavaScript Framework', 'Database System', 'Operating System', 'Programming Language', 'Web Browser', NULL),
(2, 'Which directive is used for two-way binding in Vue.js?', 'v-model', 'v-bind', 'v-for', 'v-if', 'v-show', NULL),
(1, 'Django is written in which programming language?', 'Python', 'Java', 'JavaScript', 'PHP', 'Ruby', NULL),
(2, 'What command is used to create a new Django project?', 'django-admin startproject', 'django create-project', 'django new-project', 'django init', 'django start', NULL),
(1, 'What is Spring Boot?', 'Java-based framework for creating microservices', 'JavaScript framework', 'Python web framework', 'Database system', 'Operating system', NULL),
(2, 'Which annotation is used to mark a class as a Spring Boot application?', '@SpringBootApplication', '@Application', '@SpringApplication', '@Boot', '@SpringBoot', NULL),
(1, 'Express.js is a framework for which platform?', 'Node.js', 'Python', 'Java', 'PHP', 'Ruby', NULL),
(2, 'Which method is used to handle GET requests in Express.js?', 'app.get()', 'app.post()', 'app.request()', 'app.fetch()', 'app.handle()', NULL),
(1, 'MongoDB is what type of database?', 'NoSQL', 'SQL', 'NewSQL', 'RelationalDB', 'GraphDB', NULL),
(2, 'What is the MongoDB equivalent of a table in relational databases?', 'Collection', 'Table', 'Array', 'Group', 'Set', NULL);

-- Continue with remaining questions...
INSERT INTO Questions (ClassificationId, QuestionStatement, CorrectAnswer, Choice1, Choice2, Choice3, Choice4, ImagePath) VALUES
(1, 'PostgreSQL is what type of database?', 'Relational', 'NoSQL', 'Graph', 'Document', 'Key-Value', NULL),
(2, 'Which command shows all databases in PostgreSQL?', '\l', 'SHOW DATABASES', 'LIST DATABASES', 'DISPLAY DBS', 'GET DBS', NULL),
(1, 'What type of database is Redis?', 'In-memory data structure store', 'Relational database', 'Document database', 'Graph database', 'Column database', NULL),
(2, 'What is the default port for Redis?', '6379', '3306', '5432', '27017', '8080', NULL),
(1, 'What does Git stand for?', 'Global Information Tracker', 'General Interface Tool', 'Global Interface Tracker', 'General Information Tool', 'None of these', NULL),
(2, 'Which command creates a new Git repository?', 'git init', 'git start', 'git create', 'git new', 'git begin', NULL),
(1, 'What is Docker?', 'Container platform', 'Operating System', 'Programming Language', 'Database System', 'Web Server', NULL),
(2, 'What command is used to run a Docker container?', 'docker run', 'docker start', 'docker execute', 'docker begin', 'docker launch', NULL),
(1, 'What is Kubernetes?', 'Container orchestration platform', 'Programming language', 'Database system', 'Operating system', 'Web server', NULL),
(2, 'What is the smallest unit in Kubernetes?', 'Pod', 'Container', 'Node', 'Cluster', 'Service', NULL),
(1, 'What is AWS?', 'Cloud computing platform', 'Web browser', 'Operating system', 'Programming language', 'Database system', NULL),
(2, 'Which AWS service is used for computing?', 'EC2', 'S3', 'RDS', 'Route53', 'CloudFront', NULL);

INSERT INTO QTRelationship (QuestionId, TopicId) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 2),
(6, 2),
(7, 3),
(8, 3),
(9, 4),
(10, 4),
(11, 5),
(12, 5),
(13, 6),
(14, 6),
(15, 7),
(16, 7),
(17, 8),
(18, 8),
(19, 9),
(20, 9),
(21, 10),
(22, 10),
(23, 11),
(24, 11),
(25, 12),
(26, 12),
(27, 13),
(28, 13),
(29, 14),
(30, 14),
(31, 15),
(32, 15),
(33, 16),
(34, 16),
(35, 17),
(36, 17),
(37, 18),
(38, 18),
(39, 19),
(40, 19),
(41, 20),
(42, 20);