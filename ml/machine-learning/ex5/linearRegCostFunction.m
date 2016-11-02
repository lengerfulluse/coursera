function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%

% caculate cost function of linear regression.
Err = X*theta - y;
Reg = theta.*theta;
J = sum(Err.*Err)/(2*m) + lambda/(2*m) * sum(Reg(2:end));

% caculate gradient of cost function.
grad = X'* Err/m + lambda*theta/m;
grad(1) = sum(Err.*X(:,1))/m;











% =========================================================================

grad = grad(:);

end
