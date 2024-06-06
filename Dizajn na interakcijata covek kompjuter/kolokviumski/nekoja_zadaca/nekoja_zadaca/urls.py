"""
URL configuration for nekoja_zadaca project.

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/5.0/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.conf.urls.static import static
from django.contrib import admin
from django.urls import path

from nekoja_zadaca import settings
from zadaca.views import *

urlpatterns = [
    path('admin/', admin.site.urls),
    path('index/', index, name='index'),
    path('newevent/', newevent, name='newevent'),
    path('editevent/<int:event_id>', editevent, name='editevent'),
    path('deleteevent/<int:event_id>', deleteevent, name='deleteevent'),
] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
