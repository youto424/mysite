document.addEventListener('DOMContentLoaded', () => {
  const display = document.getElementById('display');
  const calculator = document.getElementById('calculator');

  let currentOperand = '0';
  let previousOperand = '';
  let operation = null; 
  let shouldResetDisplay = false;

  calculator.addEventListener('click', (event) => {
    const target = event.target;

    if (!target.matches('button')) return;

    if (target.matches('.number')) {
      const number = target.dataset.number;
      appendNumber(number);
      updateDisplay();
    }

    if (target.matches('.operator')) {
      const op = target.dataset.operator;
      chooseOperation(op);
      updateDisplay();
    }

    if (target.matches('.equals')) {
      calculate();
      updateDisplay();
    }

    if (target.matches('.clear')) {
      clear();
      updateDisplay();
    }
  });

  function appendNumber(number) {
    if (currentOperand === '0' || shouldResetDisplay) {
      currentOperand = number;
      shouldResetDisplay = false;
    } else {
      if (number === '.' && currentOperand.includes('.')) return;
      currentOperand += number;
    }
  }

  function chooseOperation(op) {
    if (currentOperand === '' && operation !== null) {
      operation = op;
      return;
    }
    
    if (previousOperand !== '') {
      calculate();
    }
    
    operation = op;
    previousOperand = currentOperand;
    currentOperand = '';
    shouldResetDisplay = false;
  }

  function calculate() {
    if (operation === null || previousOperand === '' || currentOperand === '') {
      return;
    }

    let result;
    const prev = parseFloat(previousOperand);
    const current = parseFloat(currentOperand);

    if (isNaN(prev) || isNaN(current)) return;

    switch (operation) {
      case '+':
        result = prev + current;
        break;
      case '-':
        result = prev - current;
        break;
      case '*':
        result = prev * current;
        break;
      case '/':
        if (current === 0) {
          console.error('0で割ることはできません');
          display.value = 'Error';
          setTimeout(clear, 1000); 
          return;
        }
        result = prev / current;
        break;
      default:
        return;
    }

    currentOperand = result.toString();
    operation = null;
    previousOperand = '';
    shouldResetDisplay = true;
  }

  function clear() {
    currentOperand = '0';
    previousOperand = '';
    operation = null;
    shouldResetDisplay = false;
    updateDisplay();
  }

function updateDisplay() {
    if (operation !== null && previousOperand !== '') {
      display.value = `${previousOperand} ${operation} ${currentOperand}`;
    } else {
      display.value = currentOperand || '0';
    }
  }
  
  document.querySelectorAll('.btn').forEach(btn => {
    btn.classList.add(
      'text-2xl',
      'font-bold',
      'py-4',
      'rounded-md',
      'transition',
      'duration-150',
      'ease-in-out',
      'focus:outline-none',
      'focus:ring-2',
      'focus:ring-offset-2',
      'focus:ring-blue-400'
    );
  });

  updateDisplay();
});
