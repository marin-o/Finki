import datetime

from django.db import models
from django.forms import ImageField


# Create your models here.

class PublishingHouse(models.Model):
    name = models.CharField(max_length=100)
    country = models.CharField(max_length=100)
    city = models.CharField(max_length=100)
    year = models.IntegerField()
    website = models.URLField()

    def __str__(self):
        return self.name


def current_year():
    return datetime.date.today().year


def date_choices():
    # return all dates from 1970 to curren year
    return [(i,i) for i in range(1970, current_year() + 1)]


class Book(models.Model):
    title = models.CharField(max_length=100)
    image = models.ImageField(upload_to='images/')
    isbn = models.CharField(max_length=100)
    publish_year = models.IntegerField(choices=date_choices())
    publishing_house = models.ForeignKey(PublishingHouse, on_delete=models.CASCADE)
    pages = models.IntegerField()
    dimensions = models.CharField(max_length=100)

    CVR_SOFT = 'SO'
    CVR_HARD = 'HD'
    cover_choices = [
        (CVR_SOFT, 'Softcover'),
        (CVR_HARD, 'Hardcover')
    ]
    cover_type = models.CharField(max_length=2, choices=cover_choices)
    GEN_ROMANCE = 'ROM'
    GEN_THRILLER = 'THR'
    GEN_BIOGRAPHY = 'BIO'
    GEN_CLASSIC = 'CLS'
    GEN_DRAMA = 'DRM'
    GEN_HISTORY = 'HST'
    gen_choices = [
        (GEN_ROMANCE, 'Romance'),
        (GEN_THRILLER, 'Thriller'),
        (GEN_BIOGRAPHY, 'Biography'),
        (GEN_CLASSIC, 'Classic'),
        (GEN_DRAMA, 'Drama'),
        (GEN_HISTORY, 'History')
    ]
    gen_type = models.CharField(max_length=3, choices=gen_choices)
    price = models.IntegerField()
