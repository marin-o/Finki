from django.contrib.auth.models import User
from django.db import models
from django.forms import IntegerField, BooleanField


# Create your models here.
class Employee(models.Model):
    employee_types = {"Cleaner": "Cleaner", "Receptionist": "Receptionist", "Manager": "Manager"}

    name = models.CharField(max_length=100)
    surname = models.CharField(max_length=100)
    work_description = models.CharField(max_length=200)
    employee_type = models.CharField(max_length=20, choices=employee_types.items(), default="Cleaner")


class Room(models.Model):
    number = IntegerField()
    beds = IntegerField()
    terrace = BooleanField()
    is_clean = BooleanField()

    def __str__(self):
        return self.number


class Reservation(models.Model):
    code = models.CharField(max_length=10)
    start_date = models.DateField()
    end_date = models.DateField()
    id_image = models.ImageField(upload_to='images/')
    confirmed = models.BooleanField(default=False)
    room = models.ForeignKey(Room, on_delete=models.CASCADE)
    creator = models.ForeignKey(User, on_delete=models.CASCADE)
    employee = models.ForeignKey(Employee, on_delete=models.CASCADE)
