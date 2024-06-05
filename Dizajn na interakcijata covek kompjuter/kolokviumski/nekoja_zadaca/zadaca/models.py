from django.contrib.auth.models import User
from django.db import models

# Create your models here.


class Event(models.Model):
    name = models.CharField(max_length=100)
    datetime = models.DateTimeField()
    poster = models.ImageField(upload_to='images/')
    creator = models.ForeignKey(User, on_delete=models.CASCADE)
    location = models.CharField(max_length=100)
    open_air = models.BooleanField()
    participants = models.CharField(max_length=200, blank=True, null=True)

    def __str__(self):
        return self.name


class Band(models.Model):
    name = models.CharField(max_length=100)
    genre = models.CharField(max_length=100)
    country = models.CharField(max_length=100)
    year_formed = models.IntegerField()
    number_of_shows = models.IntegerField()

    def __str__(self):
        return self.name


class BandEvent(models.Model):
    band = models.ForeignKey(Band, on_delete=models.CASCADE)
    event = models.ForeignKey(Event, on_delete=models.CASCADE)

    def __str__(self):
        return f'{self.band.name} - {self.event.name}'
