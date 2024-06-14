from django.contrib import admin

# Register your models here.

from .models import Book, PublishingHouse


@admin.register(Book)
class BookAdmin(admin.ModelAdmin):
    def has_add_permission(self, request):
        if request.user.is_superuser:
            return True
        return False

    list_display = ['title', 'publishing_house']


@admin.register(PublishingHouse)
class PublishingHouseAdmin(admin.ModelAdmin):
    def has_add_permission(self, request):
        if request.user.is_superuser:
            return True
        return False