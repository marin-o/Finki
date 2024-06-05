from django.contrib import admin
from .models import Event, Band, BandEvent
# Register your models here.


class BandInline(admin.TabularInline):
    model = BandEvent
    extra = 1


class EventAdmin(admin.ModelAdmin):
    inlines = [BandInline]
    exclude = ('creator','participants')
    list_display = ('name','datetime',)

    def has_add_permission(self, request):
        if request.user.is_superuser:
            return True
        return False

    def has_delete_permission(self, request, obj=None):
        if obj and request.user == obj.creator:
            return True
        return False

    def has_change_permission(self, request, obj=None):
        if obj and request.user == obj.creator:
            return True
        return False

    def save_model(self, request, obj, form, change):
        obj.creator = request.user
        return super().save_model(request, obj, form, change)


class BandAdmin(admin.ModelAdmin):
    list_display = ('name', 'country',)


admin.site.register(Event, EventAdmin)
admin.site.register(Band, BandAdmin)
admin.site.register(BandEvent)
