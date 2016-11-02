function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%

%A1 = load('ex2data1.txt');
%idx_0 = find(A1(:,3) == 0);
%A1_0 = A1(idx_0,:);
%idx_1 = find(A1(:,3) == 1);
%A1_1 = A1(idx_1,:);
%X1_0 = A1_0(:,1);
%Y1_0 = A1_0(:,2);
%X1_1 = A1_1(:,1);
%Y1_1 = A1_1(:,2);
%plot(X1_0, Y1_0, 'o', "markerfacecolor",'y', "markeredgecolor",'k');
%plot(X1_1, Y1_1, 'k+');
%xlabel "Exam 1 score"
%ylabel "Exam 2 score"
%legend("Not Admitted", "Admitted");
pos = find(y == 1);
neg = find(y == 0);
plot(X(pos,1), X(pos,2), 'k+');
plot(X(neg,1), X(neg,2), 'o', "markerfacecolor",'y', "markeredgecolor",'k');






% =========================================================================



hold off;

end
