from django.forms import ModelForm

from zadaca.models import Event


class EventForm(ModelForm):

    class Meta:
        model = Event
        exclude = ['creator', 'datetime',]
        fields = '__all__'
