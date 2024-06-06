from django.forms import ModelForm

from zadaca.models import Event


class EventForm(ModelForm):

    def __init__(self, *args, **kwargs):
        super(EventForm, self).__init__(*args, **kwargs)
        for field in self.visible_fields():
            # додавање бутстрап во форма
            field.field.widget.attrs["class"] = "form-control"
            # додавање чекбокс бутстрап во форма
            if field.name == 'open_air':
                field.field.widget.attrs["class"] = "form-check-input"
    class Meta:
        model = Event
        exclude = ['creator', 'datetime',]
        fields = '__all__'
