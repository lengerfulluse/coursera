function [J grad] = nnCostFunction(nn_params, ...
                                   input_layer_size, ...
                                   hidden_layer_size, ...
                                   num_labels, ...
                                   X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices. 
% 
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
                 hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
                 num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);
         
% You need to return the following variables correctly 
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a 
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the 
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%




X = [ones(m,1) X];

for label = 1:num_labels
	labels(label) = label;
endfor

y_binary = zeros(m,num_labels);
for i = 1:m
    pos = find(labels == y(i));
    y_binary(i,pos) = 1;
endfor


Z1 = X*Theta1';

A1 = sigmoid(Z1);

A1 = [ones(m,1) A1];

Z2 = A1*Theta2';

A2 = sigmoid(Z2);

M = -1*y_binary.*log(A2) - (1-y_binary).*log(1-A2);

% caculate the regularized item.
Theta1_Squa = Theta1.*Theta1;
Theta2_Squa = Theta2.*Theta2;
R = lambda*(sum(sum(Theta1_Squa(:,2:end), 2)) + sum(sum(Theta2_Squa(:,2:end),2)))/(2*m);

J = sum(sum(M, 2))/m + R;

% backpropogration to caculate the gradient.

Delta3 = A2 - y_binary;
size(Delta3);
N = Delta3*Theta2;
Delta2 = N(:,2:end).*sigmoidGradient(Z1);
size(Delta2);

Theta2_grad = Theta2_grad + Delta3'*A1;
Theta2_grad_copy = Theta2_grad;
Theta2_grad = Theta2_grad/m + lambda*Theta2/m;
Theta2_grad(:,1) = Theta2_grad_copy(:,1)/m;
size(Theta2_grad);

Theta1_grad = Theta1_grad + Delta2'*X;
Theta1_grad_copy = Theta1_grad;
Theta1_grad = Theta1_grad/m + lambda*Theta1/m;
Theta1_grad(:,1) = Theta1_grad_copy(:,1)/m;
size(Theta1_grad);















% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
