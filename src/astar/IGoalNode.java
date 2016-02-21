package astar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Raimonds Grismanausks
 */

/**
 * GoalNodes don't need as much Information
 * as SearchNodes.
 */
public interface IGoalNode{
    public boolean inGoal(ISearchNode other);
} 