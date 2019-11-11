# JudgeOnline
Judge Online - for testing programmers skills  

My own shot at implementing online judge platform for testing programmer skills.  
Super simplified temp notes:
EvaluationService should take in at least:
submitted code
problem test input
(optional) env setting / limits

and should return:
evaluation result (some data?)

evaluation module shouldnt share any database with other modules

main module can keep:
submissions (mapped to user - many to one)
problems
(later) users

and have a functionality of:
CRUD problems
creating and sending submissions to be evaluated
(later) adding/removing users

evaluation 
 depends on submission
 
submission
 depends on problems
 
 
 problems need
	description
	sample input - output
	full hidden input - output

	
first - create unsafe application
second - add user with roles and restricted access
third - secure evaluation env
fourth - ???
fifth - profit
