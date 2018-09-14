# Lesson01

Problem: In some cases we would like to create unique instance marked by unique ID (like name)

Solution: use Saga to allow create only unique instance.

Side effect:
Saga doesn't allow also create next aggregates with reserved names even if current aggregate has already changed nama.
