#Errors and exceptions
#try catch de toda la vida
try:
    a = 5 / 1;
    b = a + 4;
except ZeroDivisionError as e:
    print (e);
except TypeError as e:
    print(e);
else:
    print("everyting OK");
finally:
    print("cleaning up...");

#clases
class ValueTooHighError(Exception):
    pass

class ValueTooSmallError(Exception):
    def __init__(self,message,value):
        self.message = message;
        self.value = value;

def test_value(x):
    if x > 100:
        raise ValueTooHighError('value is too high');
    if x < 5:
        raise ValueTooSmallError('Value is too small',x)

try:
    test_value(200);
except ValueTooHighError as e:
    print(e);
except ValueTooSmallError as e:
    print (e.message,e.value)