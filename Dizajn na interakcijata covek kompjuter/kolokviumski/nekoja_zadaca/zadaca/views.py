from datetime import timezone, datetime

from django.shortcuts import render, redirect

from zadaca.forms import EventForm
from zadaca.models import Event, Band, BandEvent


# Create your views here.


def index(request):
    if request.user.is_authenticated:
        events_user = Event.objects.filter(creator=request.user)

        context = {'events': events_user}
        return render(request, 'index.html', context)
    return render(request, 'index.html', )


def newevent(request):
    if request.method == 'POST':
        event_form = EventForm(request.POST, request.FILES)
        if event_form.is_valid():
            event = event_form.save(commit=False)
            event.creator = request.user
            event.datetime = datetime.now(timezone.utc)
            event.save()
            participants = event_form.cleaned_data['participants'].split(',')
            for participant in participants:
                band = Band.objects.filter(name=participant).first()
                if band:
                    BandEvent.objects.create(band=band, event=event)

            return redirect(to='index')
    else:
        event_form = EventForm()
    context = {'form': event_form}
    return render(request, 'newevent.html', context)


def editevent(request, event_id):
    instance = Event.objects.filter(id=event_id).get()
    if request.method == 'POST':
        event_form = EventForm(request.POST, instance=instance)
        if event_form.is_valid():
            event = event_form.save(commit=False)
            event.save()
            participants = event_form.cleaned_data['participants'].split(',')
            for participant in participants:
                band = Band.objects.filter(name=participant).first()
                if band:
                    if not BandEvent.objects.filter(event=instance).exists():
                        BandEvent.objects.create(band=band, event=event)
                    for bandevent in BandEvent.objects.filter(event=instance).all():
                        if bandevent.band.name not in participants:
                            bandevent.delete()
            return redirect(to='index')
    else:
        event_form = EventForm(instance=instance)
    return render(request, "newevent.html", {'form': event_form})


def deleteevent(request, event_id):
        Event.objects.filter(id=event_id).delete()
        return redirect(to='index')
