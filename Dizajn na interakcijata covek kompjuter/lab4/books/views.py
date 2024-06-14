from django.shortcuts import render, redirect

from .forms import BookForm
from .models import Book


# Create your views here.

def index(request):
    books = Book.objects.all()
    context = {
        'books': books,
        'base_title': '',
    }
    return render(request, 'index.html', context)


def add_book(request):
    if request.method == 'POST':
        form = BookForm(request.POST, request.FILES)

        if form.is_valid():
            form.save()
            return redirect('index')
    else:
        form = BookForm()

    context = {
        'form': form,
        'base_title': 'Add Book',
    }
    return render(request, 'add_book.html', context)

def book_details(request, book_id):
    book = Book.objects.get(id=book_id)
    context = {
        'book': book,
        'base_title': book.title,
    }
    return render(request, 'book_details.html', context)
