# Lesson01

Problem: In some cases we would like to create unique instances

Solution: use Saga to allow create only oneunique instance.

Side effect:
Saga doesn't allow also create next aggregates with reserved names even if current aggregate has already changed nama.
